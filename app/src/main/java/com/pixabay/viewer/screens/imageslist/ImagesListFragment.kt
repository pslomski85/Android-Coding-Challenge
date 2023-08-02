package com.pixabay.viewer.screens.imageslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pixabay.viewer.Constants.HIT_ARGS
import com.pixabay.viewer.R
import com.pixabay.viewer.models.Hit
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesListFragment: Fragment(), ImagesListAdapter.Listener {

    private val imagesListAdapter = ImagesListAdapter(arrayListOf(), this)
    private lateinit var viewModel: ImagesListViewModel
    private lateinit var imagesList: RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(ImagesListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagesList = view.findViewById(R.id.imagesList)

        refreshLayout = view.findViewById(R.id.refreshLayout)
        refreshLayout.setOnRefreshListener {
            viewModel.getQuery()
            refreshLayout.isRefreshing = false
        }
        setupMenu()
        setupRecyclerView()
        observeViewModel()

    }

    private fun setupRecyclerView() {
        imagesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = imagesListAdapter
            addOnScrollListener(ImagesListScrollListener(viewModel, imagesList))
        }
    }
    private fun setupMenu() {

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.search_menu, menu)

                val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
                searchView = searchItem?.actionView as SearchView

                searchView.onActionViewExpanded()
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        viewModel.setupQueryParameters(newText)
                        return true
                    }
                })
                searchView.setQuery(viewModel.getQuery(), true)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun observeViewModel() {
        viewModel.images.observe(viewLifecycleOwner, Observer { images ->
            images?.let {
                imagesListAdapter.updateImageList(images.hits)
            }
        })
    }

    override fun onImageClicked(clickedImage: Hit) {
        searchView.setOnQueryTextListener(null);
        findNavController().navigate(
            R.id.action_imagesListFragment_to_questionDetailsDialogFragment,
            Bundle().apply { putSerializable(HIT_ARGS, clickedImage) }
        )
    }

}