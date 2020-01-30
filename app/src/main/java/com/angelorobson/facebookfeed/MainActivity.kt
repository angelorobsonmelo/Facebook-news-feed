package com.angelorobson.facebookfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.feed_item.view.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = top_nav
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.feed))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.request))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.users))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.watch))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.notify))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.more))

        val recyclerView = recycler_view

        val dividerItemDecoration =
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)

        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)


        with(recyclerView) {
            addItemDecoration(dividerItemDecoration)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(getPosts())
        }

    }

    private fun getPosts(): MutableList<Post> {
        return mutableListOf(
            Post(
                R.drawable.jon_snow,
                R.drawable.post_1,
                "2 min",
                "Jon Snow",
                "No matter what Ygritte says, Jon Snow knows a few things. The bastard son of Ned Stark (or is he?) grew up in a household where he was welcomed by Ned and his half-siblings but not so much by Catelyn Stark. When the opportunity came to join the Night's Watch at Castle Black, he saw",
                "gameofthrones.com".toUpperCase(),
                "Game of Thrones is an American fantasy drama television series created by David Benioff and D. B. Weiss. It is an adaptation of A Song of Ice and Fire, George R. R. Martin's series of fantasy novels, the first of which is A Game of Thrones"
            ),

            Post(
                R.drawable.red_queen,
                R.drawable.post_2,
                "12 min",
                "Melisandre",
                "My ability to see visions in the flames, and completely trusts in the power of her god, R'hllor. Although she acknowledges that visions can be misinterpreted,[8] she has faith in her ability to correctly interpret visions, even if the vision does not entirely agree with the proposed interpretation.[4]"

            ),

            Post(
                R.drawable.sansa,
                R.drawable.post_3,
                "45 min",
                "Sansa Stark",
                "For years we’ve discovered the wonderfully complex world of Westeros and followed a medley of different stories, locations, and characters, but the heart of the series has always been with the Stark family."

            )
        )
    }


    private class PostAdapter(private val posts: MutableList<Post>) :
        RecyclerView.Adapter<PostViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
            PostViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.feed_item,
                    parent,
                    false
                )
            )


        override fun getItemCount(): Int = posts.size

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.bind(posts[position])
        }

    }


    private class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(post: Post) {
            with(itemView) {
                image_view_post.setImageResource(post.imageViewPost)
                image_view_user.setImageResource(post.imageViewUser)

                text_view_time.text = post.textViewTime
                text_view_username.text = post.textViewUsername
                text_view_content.text = post.textViewContent
                text_view_post_title.text = post.textViewTitle
                text_view_sub_title.text = post.textViewSubtitle

                if (post.textViewTitle.isEmpty()) {
                    post_container.visibility = View.GONE
                    val constraintSet = ConstraintSet()

                    constraintSet.clone(this as ConstraintLayout)
                    constraintSet.setDimensionRatio(R.id.image_view_post, "1:1")
                    constraintSet.applyTo(this)

                } else {
                    post_container.visibility = View.VISIBLE
                    val constraintSet = ConstraintSet()

                    constraintSet.clone(this as ConstraintLayout)
                    constraintSet.setDimensionRatio(R.id.image_view_post, "16:9")
                    constraintSet.applyTo(this)
                }
            }
        }

    }


    private data class Post(
        val imageViewUser: Int,
        val imageViewPost: Int,
        val textViewTime: String,
        val textViewUsername: String,
        val textViewContent: String,
        val textViewTitle: String,
        val textViewSubtitle: String
    ) {
        constructor(
            imageViewUser: Int,
            imageViewPost: Int,
            textViewTime: String,
            textViewUsername: String,
            textViewContent: String
        ) : this(
            imageViewUser,
            imageViewPost,
            textViewTime,
            textViewUsername,
            textViewContent,
            "",
            ""
        )
    }

}
