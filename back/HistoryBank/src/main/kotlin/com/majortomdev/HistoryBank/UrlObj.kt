@Entity
data class UrlObj(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val url: String,
    @ManyToOne @JoinColumn(name = "user_id")
    val user: User
)
