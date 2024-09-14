@Service
class UrlService(
    private val urlRepository: UrlRepository,
    private val userRepository: UserRepository
) {

    fun saveUrl(urlObj: UrlObj, username: String): UrlObj {
        val user = userRepository.findByUsername(username)
            ?: throw RuntimeException("User not found")

        val newUrl = UrlObj(url = urlObj.url, user = user)
        return urlRepository.save(newUrl)
    }

    fun getUrlsForUser(username: String): List<UrlObj> {
        val user = userRepository.findByUsername(username)
            ?: throw RuntimeException("User not found")

        return urlRepository.findByUserId(user.id)
    }

    fun deleteUrl(urlId: Int, username: String) {
        val user = userRepository.findByUsername(username)
            ?: throw RuntimeException("User not found")

        val url = urlRepository.findById(urlId)
            .orElseThrow { RuntimeException("URL not found") }

        if (url.user.id != user.id) {
            throw RuntimeException("You are not authorized to delete this URL")
        }

        urlRepository.delete(url)
    }
}
