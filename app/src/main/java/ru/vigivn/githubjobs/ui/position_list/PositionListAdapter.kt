package ru.vigivn.githubjobs.ui.position_list

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.vigivn.githubjobs.databinding.PositionItemBinding
import ru.vigivn.githubjobs.model.Position
import ru.vigivn.githubjobs.ui.position_details.PositionDetailsActivity

class PositionListAdapter: RecyclerView.Adapter<PositionListAdapter.ViewHolder>() {
    private val items: MutableList<Position> = ArrayList()

    fun setData(positions: List<Position>) {
        items.clear()
        items.addAll(positions)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PositionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val binding: PositionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Position) {
            with (binding) {
                title.text = position.title
                location.text = position.location
            }
            Picasso.get().load(position.companyLogo).into(binding.logo)

            itemView.setOnClickListener {
                val intent = Intent(binding.root.context, PositionDetailsActivity::class.java)
                intent.putExtra("id", position.id)
                binding.root.context.startActivity(intent)
            }
        }
    }
}