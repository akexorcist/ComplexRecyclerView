package com.akexorcist.complexrecyclerviewr

import android.os.Bundle
import android.widget.Toast
import com.akexorcist.complexrecyclerviewr.adapter.ArticleCategoryFactory
import com.akexorcist.complexrecyclerviewr.adapter.ArticleGroupFactory
import com.akexorcist.complexrecyclerviewr.adapter.ProfileFactory
import com.akexorcist.complexrecyclerviewr.adapter.article.category.ArticleCategoryItem
import com.akexorcist.complexrecyclerviewr.adapter.article.group.ArticleGroupItem
import com.akexorcist.complexrecyclerviewr.adapter.article.topic.ArticleTopicItem
import com.akexorcist.complexrecyclerviewr.api.MockApi
import com.akexorcist.library.complexrecyclerview.core.ComplexAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : com.akexorcist.library.complexrecyclerview.core.StateHandleHelperActivity() {
    private val profileFactory: ProfileFactory by lazy { ProfileFactory(stateHandler) }
    private val categoryFactory: ArticleCategoryFactory by lazy {
        ArticleCategoryFactory(stateHandler).apply {
            setListener(articleCategoryListener)
        }
    }
    private val groupFactory: ArticleGroupFactory by lazy {
        ArticleGroupFactory(stateHandler).apply {
            setListener(articleGroupListener)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // แบ่ง ViewHolder เป็นกลุ่มด้วย Factory เพื่อจัดการ View แต่ละชุดที่ทำงานซับซ้อน - Done
        // สามารถส่ง Event กลับมาได้ โดยมี Factory คั่น - Done
        // จำเป็นต้องมี Controller มั้ย? (สร้างด้วย Factory ควบคุมการทำงานด้วย Controller) - No Need
        // ViewHolder ยังคงโง่เหมือนเดิม - Done
        // Adapter คอย Render ตามที่ Factory ที่ส่งเข้ามา - Done
        // ใช้เป็น GridLayoutManager ไปเลย จะได้รองรับหลาย Column - Done
        // จัดการ State แยกกับ Data ได้มั้ย? (มองเป็น Prop กับ State ไรงี้) - Done
        // อยากให้ใช้ DiffUtil ด้วย
        // ถ้าจัดการพวก Load More ได้ง่ายๆด้วย จะดีมาก
        // ถ้ารองรับกับ Sticky Header ก็ดีนะ

        val factoryList: ArrayList<in ComplexAdapter.Factory<Any>> = arrayListOf(
            profileFactory,
            categoryFactory,
            groupFactory
        )
        recyclerView.setFactoryList(factoryList)
        getProfile()
        getArticleCategory()
    }

    private fun getProfile() {
        MockApi.getProfile().let { data ->
            profileFactory.update(data)
        }
    }

    private fun getArticleCategory() {
        MockApi.getArticleCategories().let { result ->
            categoryFactory.update(result)
            categoryFactory.setCategorySelected(0)
            result.categoryList?.get(0)?.id?.let { id ->
                updateArticleGroup(id)
            }
        }
    }

    private fun updateArticleGroup(groupId: String?) {
        MockApi.getArticleGroup(groupId)?.let { result ->
            groupFactory.update(result)
            groupFactory.toggleGroup(0)
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private val articleCategoryListener = object : ArticleCategoryFactory.Listener {
        override fun onCategorySelected(item: ArticleCategoryItem) {
            categoryFactory.setCategorySelected(item)
            recyclerView.adapter?.notifyDataSetChanged()
            updateArticleGroup(item.data?.id)
        }
    }

    private val articleGroupListener = object : ArticleGroupFactory.Listener {
        override fun onGroupClick(item: ArticleGroupItem) {
            groupFactory.toggleGroup(item)
            recyclerView.adapter?.notifyDataSetChanged()
        }

        override fun onTopicClick(item: ArticleTopicItem) {
            item.data?.title?.let { title ->
                Toast.makeText(this@MainActivity, title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
