package com.filippoengidashet.spacex.mvvm.view.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.filippoengidashet.spacex.R
import com.filippoengidashet.spacex.common.BaseFragment
import com.filippoengidashet.spacex.common.DateHelper
import com.filippoengidashet.spacex.common.ImageLoader
import com.filippoengidashet.spacex.mvvm.model.entity.bean.*
import com.filippoengidashet.spacex.mvvm.vm.CompanyViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

/**
 * @author Filippo 10/11/2021
 */
@AndroidEntryPoint
class FragmentMain : BaseFragment(R.layout.fragment_main) {

    private val mainViewModel: CompanyViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var dateHelper: DateHelper

    private lateinit var rvLaunches: RecyclerView
    private lateinit var textCompanyInfo: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var listAdapter: LaunchesAdapter

    private var filterDialogShowing: Boolean = false
    private var cachedCompanyData: CompanyData? = null
    private var decoration: DividerItemDecoration? = null

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        listAdapter = LaunchesAdapter(requireContext(), dateHelper, imageLoader) { item ->
            openOptionDialog(item)
        }
    }

    private fun openOptionDialog(item: Launch) {
        OpeningOptionsDialog.show(
            parentFragmentManager, item.article, item.wikipedia, item.webcast
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_filter) {
            if (!filterDialogShowing) showFilterDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showFilterDialog() {
        filterDialogShowing = true
        val filterView = layoutInflater.inflate(
            R.layout.dialog_filter_options, null, false
        )

        val yearSpinnerView = filterView.findViewById<Spinner>(R.id.spinner_years).also {
            it.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                dateHelper.getYearRange()
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        }
        val succeededSwitch = filterView.findViewById<SwitchCompat>(R.id.switch_succeeded)
        val sortOrderSpinnerView = filterView.findViewById<Spinner>(R.id.spinner_sort_order)

        val builder = AlertDialog.Builder(requireActivity())
            .setTitle(getString(R.string.text_title_filter))
            .setCancelable(true)
            .setOnDismissListener {
                filterDialogShowing = false
            }
            .setView(filterView)
            .setPositiveButton(getString(R.string.text_action_filter)) { _, _ ->
                val year = yearSpinnerView.selectedItem.toString().toInt()
                val launchSuccess = succeededSwitch.isChecked
                val order = sortOrderSpinnerView.selectedItem.toString()
                filterLaunches(
                    FilterOption(year, launchSuccess, SortOrder.from(order))
                )
            }
            .setNegativeButton(getString(R.string.text_action_reset_default)) { d, _ ->
                cachedCompanyData?.launches?.let {
                    setLaunches(it)
                }
                d.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }

    private fun filterLaunches(option: FilterOption) {
        cachedCompanyData?.launches?.also { launches ->

            val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

            val yearMatches: (Launch) -> Boolean = { launch ->
                cal.timeInMillis = launch.dateMillis
                option.year == cal.get(Calendar.YEAR)
            }

            val newList = launches.filter { launch ->
                val hasIt = if (option.launchSuccess) launch.succeeded else !launch.succeeded
                return@filter hasIt && yearMatches(launch)
            }
            val sorted = if (option.sortOrder == SortOrder.ASC) {
                newList.sortedBy { it.dateMillis }
            } else {
                newList.sortedByDescending { it.dateMillis }
            }
            setLaunches(sorted)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progress_bar)
        textCompanyInfo = view.findViewById(R.id.text_company_info)
        rvLaunches = view.findViewById<RecyclerView>(R.id.list_company_data).apply {
            setHasFixedSize(true)
            decoration?.let {
                addItemDecoration(
                    DividerItemDecoration(requireContext(),
                        DividerItemDecoration.VERTICAL).also {
                        decoration = it
                    }
                )
            }
            adapter = listAdapter
        }
        mainViewModel.getCompanyLiveData().observe(viewLifecycleOwner) { result ->
            when(result) {
                is ResultBean.Succeeded -> {
                    cachedCompanyData = result.data
                    progressBar.visibility = View.GONE
                    cachedCompanyData?.info?.also { info ->
                        textCompanyInfo.text = getString(
                            R.string.text_company_info,
                            info.name,
                            info.founder,
                            info.year,
                            info.employees,
                            info.launchSites,
                            info.valuation
                        )
                    }
                    cachedCompanyData?.launches?.let { launches ->
                        setLaunches(launches = launches, scrollToTop = false)
                    }
                }
                is ResultBean.Failed -> {
                    showToast(result.error)
                }
            }
        }
        mainViewModel.load()
    }

    private fun setLaunches(launches: List<Launch>, scrollToTop: Boolean = true) {
        listAdapter.setLaunches(launches)
        if (scrollToTop) {
            rvLaunches.scrollToPosition(0)
        }
    }

    override fun onDestroyView() {
        decoration?.let {
            rvLaunches.removeItemDecoration(it)
        }
        super.onDestroyView()
    }
}
