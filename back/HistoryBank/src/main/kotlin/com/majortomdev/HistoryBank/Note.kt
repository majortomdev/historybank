@Entity
data class Note(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val title: String,
    @ElementCollection
    val notes: List<String> = listOf(),
    @ManyToOne @JoinColumn(name = "user_id")
    val user: User
)
