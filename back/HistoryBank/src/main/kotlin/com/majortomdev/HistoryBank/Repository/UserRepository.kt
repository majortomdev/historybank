interface UserRepository : JpaRepository<User, Int> {
    fun findByUsername(username: String): User?
}
