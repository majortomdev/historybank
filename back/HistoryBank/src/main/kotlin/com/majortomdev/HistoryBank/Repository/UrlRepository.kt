interface UrlRepository : JpaRepository<UrlObj, Int> {
    fun findByUserId(userId: Int): List<UrlObj>
}
