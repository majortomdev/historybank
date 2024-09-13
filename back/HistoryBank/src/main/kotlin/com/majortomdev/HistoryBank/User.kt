@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val username: String,
    val password: String,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val urls: List<UrlObj> = listOf(),
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val notes: List<Note> = listOf()
)