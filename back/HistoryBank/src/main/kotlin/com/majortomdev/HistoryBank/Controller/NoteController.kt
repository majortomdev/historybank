@RestController
@RequestMapping("/api/notes")
class NoteController(
    private val noteService: NoteService
) {

    @PostMapping
    fun createNote(@RequestBody noteDto: NoteDto, principal: Principal): ResponseEntity<Note> {
        val note = noteService.createNote(Note(title = noteDto.title, notes = noteDto.notes), principal.name)
        return ResponseEntity.ok(note)
    }

    @GetMapping
    fun getAllNotes(principal: Principal): ResponseEntity<List<Note>> {
        val notes = noteService.getNotesForUser(principal.name)
        return ResponseEntity.ok(notes)
    }

    @PutMapping("/{id}")
    fun updateNote(
        @PathVariable id: Int,
        @RequestBody noteDto: NoteDto,
        principal: Principal
    ): ResponseEntity<Note> {
        val updatedNote = noteService.updateNote(id, Note(title = noteDto.title, notes = noteDto.notes), principal.name)
        return ResponseEntity.ok(updatedNote)
    }

    @DeleteMapping("/{id}")
    fun deleteNote(@PathVariable id: Int, principal: Principal): ResponseEntity<Void> {
        noteService.deleteNote(id, principal.name)
        return ResponseEntity.noContent().build()
    }
}
