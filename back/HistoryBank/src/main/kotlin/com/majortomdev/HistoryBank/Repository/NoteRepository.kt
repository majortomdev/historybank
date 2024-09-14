interface NoteRepository : JpaRepository<Note, Int> {
    fun findByUserId(userId: Int): List<Note>
}
