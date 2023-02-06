package com.example.astronovos.data.model

data class Post(
    val id: Int,
    val title: String,
    val url: String,
    val imageUrl: String,
    val summary: String,
    val publishedAt: String,
    val updatedAt: String?,
    var launches: Array<Launch> = emptyArray()
) {
   /**
    * Функция hasLaunch() — это удобный метод для
    * указать, есть ли выпуски, связанные с новостью.
    */
   fun hasLaunch(): Boolean = launches.isNotEmpty()

   fun getLaunchCount() : Int = launches.size

   /**
    * Я переопределил методы equals() и hashCode(), чтобы улучшить
    * производительность от DiffUtil
    */
   override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (javaClass != other?.javaClass) return false

      other as Post

      if (id != other.id) return false
      if (title != other.title) return false

      return true
   }

   override fun hashCode(): Int {
      var result = id
      result = 31 * result + title.hashCode()
      return result
   }

}
