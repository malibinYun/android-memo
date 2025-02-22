package camp.nextstep.edu.memo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import camp.nextstep.edu.domain.Memo
import camp.nextstep.edu.memo.databinding.ItemMemoBinding

/**
 * Created By Malibin
 * on 8월 26, 2021
 */

class MemosAdapter : ListAdapter<Memo, MemosAdapter.ViewHolder>(ItemComparator()) {

    private var onMemoClickListener: ((Memo) -> Unit)? = null
    private var onMemoLongClickListener: ((Memo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMemoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onMemoClickListener, onMemoLongClickListener)
    }

    fun setOnMemoClickListener(listener: ((Memo) -> Unit)?) {
        this.onMemoClickListener = listener
    }

    fun setOnMemoLongClickListener(listener: ((Memo) -> Unit)?) {
        this.onMemoLongClickListener = listener
    }

    class ViewHolder(
        private val binding: ItemMemoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            memo: Memo,
            onItemClickListener: ((Memo) -> Unit)? = null,
            onItemLongClickListener: ((Memo) -> Unit)? = null,
        ) {
            binding.memo = memo
            onItemClickListener?.run { binding.root.setOnClickListener { this(memo) } }
            onItemLongClickListener?.run { binding.root.setOnLongClickListener { this(memo); true } }
        }
    }

    private class ItemComparator : DiffUtil.ItemCallback<Memo>() {
        override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
            return oldItem == newItem
        }
    }
}
