@Service
class NoteService(
    private val noteRepository: NoteRepository,
    private val userRepository: UserRepository
) {

    fun createNote(note: Note, username: String): Note {
        val user = userRepository.findByUsername(username)
            ?: throw RuntimeException("User not found")

        val newNote = Note(title = note.title, notes = note.notes, user = user)
        return noteRepository.save(newNote)
    }

    fun getNotesForUser(username: String): List<Note> {
        val user = userRepository.findByUsername(username)
            ?: throw RuntimeException("User not found")

        return noteRepository.findByUserId(user.id)
    }

    fun updateNote(noteId: Int, noteDetails: Note, username: String): Note {
        val user = userRepository.findByUsername(username)
            ?: throw RuntimeException("User not found")

        val note = noteRepository.findById(noteId)
            .orElseThrow { RuntimeException("Note not found") }

        if (note.user.id != user.id) {
            throw RuntimeException("You are not authorized to update this note")
        }

        note.title = noteDetails.title
        note.notes = noteDetails.notes
        return noteRepository.save(note)
    }

    fun deleteNote(noteId: Int, username: String) {
        val user = userRepository.findByUsername(username)
            ?: throw RuntimeException("User not found")

        val note = noteRepository.findById(noteId)
            .orElseThrow { RuntimeException("Note not found") }

        if (note.user.id != user.id) {
            throw RuntimeException("You are not authorized to delete this note")
        }

        noteRepository.delete(note)
    }
}
