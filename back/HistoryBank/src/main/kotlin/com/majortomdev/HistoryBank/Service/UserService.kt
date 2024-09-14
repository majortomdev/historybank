@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) {

    fun register(userDto: UserDto): User {
        if (userRepository.findByUsername(userDto.username) != null) {
            throw RuntimeException("User already exists")
        }

        val encodedPassword = passwordEncoder.encode(userDto.password)
        val user = User(username = userDto.username, password = encodedPassword)
        return userRepository.save(user)
    }

    fun login(loginDto: LoginDto): String {
        val user = userRepository.findByUsername(loginDto.username)
            ?: throw RuntimeException("User not found")

        if (!passwordEncoder.matches(loginDto.password, user.password)) {
            throw RuntimeException("Invalid credentials")
        }

        return jwtUtil.generateToken(user.username)// Generate JWT token
    }

    fun findUserByUsername(username: String): User {
        return userRepository.findByUsername(username)
            ?: throw RuntimeException("User not found")
    }
}
