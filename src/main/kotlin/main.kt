fun main() {
    val service = WallService()
    val post = service.add(Post(id = 1, text = "Hello"))
    val post1 = service.add(Post(id = 2, text = "Hello Word"))
    val post2 = service.update(Post(id = 1, text = "Yoops"))

    println(post)
    println(post1)
    println(post2)
}

class WallService() {
    private var posts = emptyArray<Post>()

    private var identifikator: Int = 1

    fun add(post: Post): Post {
        post.id = identifikator
        posts += post
        identifikator++
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, post1) in posts.withIndex()) {
            if (post1.id == post.id) {
                posts[index] =
                    post.copy(id = post1.id)
                return true
            }
        }
        return false
    }
}

// объект, описывающий запись на стене пользователя или сообщества
data class Post(
    var id: Int = 0,                          // идентификатор записи
    val owner_id: Int = 0,
    val from_id: Int = 0,
    val created_by: Int = 0,
    val date: Int = 0,
    var text: String = "",
    val reply_owner_id: Int = 0,
    val reply_post_id: Int = 0,
    val friedly_only: Boolean = false,
    val comments: Comments? = null,
    val copyring: Copyring = Copyring(),
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val can_pin: Boolean = true,
    val can_delete: Boolean = false,
    val can_edit: Boolean = true,
    val is_pinned: Boolean = false,
    val marked_as_ads: Boolean = false,
    val is_favorite: Boolean = true,
    val donut: Donut = Donut(),
    val postponed_id: Int = 0,
    val attachment: Array<Attachment> = emptyArray()
)

// информация о комментариях к записи
data class Comments(
    val count: Int = 0,
    val can_post: Boolean = false,
    val groups_can_post: Boolean = false,
    val can_close: Boolean = false,
    val can_open: Boolean = false
)

// источник материала
data class Copyring(
    val id: Int = 0,
    val link: String = "",
    val name: String = "",
    val type: String = ""
)

// информация о лайках к записи
data class Likes(
    val count: Int = 0,
    val user_likes: Boolean = true,
    val can_like: Boolean = true,
    val can_publish: Boolean = true
)

// информация о репостах записи ("Рассказать друзьям")
data class Reposts(
    val count: Int = 0,
    val user_reposted: Boolean = false
)

// информация о просмотрах записи. Объект с единственным полем
data class Views(
    val count: Int = 0
)

// информация о записи VK Donut
data class Donut(
    val is_donut: Boolean = true,
    val paid_duration: Int = 0,
    val can_publish_free_copy: Boolean = true,
    val edit_mode: String = ""
)

abstract class Attachment(
    open val type: String
)

data class Foto(
    val id: Int = 0,                   // Идентификатор фотографии
    val date: Int = 0,               // дата добавления фотографии
    val type: String = ""               // текст описания фотографии
)

data class AttachmentFoto(val foto: Foto, override val type: String) : Attachment(type)

data class Video(
    val id: Int = 0, // идентификатор видеозаписи
    val title: String = "", // название видеозаписи
    val date: Int = 0 // дата создания видеозаписи
)

data class AttachmentVideo(val video: Video, override val type: String) : Attachment(type)

data class Audio(
    val id: Int = 0, // идентификатор аудиозаписи
    val artist: String = "", // исполнитель
    val title: String = "", // название аудиозаписи
    val date: Int = 0 // дата создания аудиозаписи
)
data class AttachmentAudio(val video: Video, override val type: String) : Attachment(type)

