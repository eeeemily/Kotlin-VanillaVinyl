package com.mzheng9.vanillavinyl.ui.webview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebViewClient
import com.mzheng9.vanillavinyl.databinding.WebviewFragmentBinding

private const val TAG = "WebviewFragment"

class WebviewFragment : Fragment() {

    private var binding: WebviewFragmentBinding? = null
    private val viewModel: WebviewViewModel by lazy {
        ViewModelProvider(this).get(WebviewViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        val webviewFragmentBinding = WebviewFragmentBinding.inflate(inflater, container, false)
        binding = webviewFragmentBinding
        return webviewFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            webView.webViewClient = WebViewClient()
            viewModel.url.observe(viewLifecycleOwner, {
                gobar.urlEditText.setText(it)
                loadUrl(it)
            })

            gobar.urlEditText.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    viewModel.setUrl(gobar.urlEditText.text.toString())
                    return@OnKeyListener true
                }
                false
            })
            gobar.goButton.setOnClickListener {
                viewModel.setUrl(gobar.urlEditText.text.toString())
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
    private fun loadUrl(request: String) {
        Log.d(TAG, "url in loadUrl: $request")
        binding?.webView?.loadUrl(request)
    }

}