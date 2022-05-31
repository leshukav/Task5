import java.lang.RuntimeException
import java.time.LocalDateTime
import kotlin.coroutines.coroutineContext

fun main() {
    val service = WallService()
    val post = service.add(Post(id = 1, text = "Hello"))
    val post1 = service.add(Post(id = 2, text = "Hello Word"))
    val post2 = service.update(Post(id = 1, text = "Yoops"))
    service.createComments(1, Comments(text = "Добавление комментария"))
    service.createComments(2, Comments(text = "Kомментариi"))
    println(post)
    println(post1)
    println(post2)
    service.printPosts()
}

class WallService() {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comments>()
    private var identifikator: Int = 1

    fun add(post: Post): Post {
        post.id = identifikator
        posts += post
        identifikator++
        return posts.last()
    }

    fun createComments(postId: Int, comment: Comments): Comments {
        for ((index, post) in posts.withIndex()) {
            if (post.id == postId) {
                comment.count++
                comments += comment
                posts[index] = post.copy(comments = comment)
                return comments.last()
            }
        }
        throw PostNotFoundException("Данного поста не существует")
    }


    fun update(post: Post): Boolean {
        for ((index, newPost) in posts.withIndex()) {
            if (newPost.id == post.id) {
                posts[index] =
                    post.copy(ownerId = newPost.ownerId, date = newPost.date)
                return true
            }
        }
        return false
    }

    fun printPosts() {
        for (post in posts) {
            println(post)
        }
    }
}

class PostNotFoundException(message: String) : RuntimeException(message)


// объект, описывающий запись на стене пользователя или сообщества
data class Post(
    var id: Int = 0,                          // идентификатор записи
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: LocalDateTime = LocalDateTime.now(),
    var text: String = "",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friedlyOnly: Boolean = false,
    val comments: Comments = Comments(),
    val copyring: Copyring = Copyring(),
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val canPin: Boolean = true,
    val canDelete: Boolean = false,
    val canEdit: Boolean = true,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = true,
    val donut: Donut = Donut(),
    val postponedId: Int = 0
)

// информация о комментариях к записи
data class Comments(
    var count: Int = 0,
    var text: String = "",   // текст комментария
    val canPost: Boolean = false,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = false,
    val canOpen: Boolean = false
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
    val userLikes: Boolean = true,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

// информация о репостах записи ("Рассказать друзьям")
data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
)

// информация о просмотрах записи. Объект с единственным полем
data class Views(
    val count: Int = 0
)

// информация о записи VK Donut
data class Donut(
    val isDonut: Boolean = true,
    val paidDuration: Int = 0,
    val canPublishFreeCopy: Boolean = true,
    val editMode: String = ""
)



