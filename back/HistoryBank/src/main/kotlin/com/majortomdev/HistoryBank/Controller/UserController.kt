@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/register")
    fun register(@RequestBody userDto: UserDto): ResponseEntity<String> {
        userService.register(userDto)
        return ResponseEntity.ok("User registered successfully")
    }

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): ResponseEntity<String> {
        val token = userService.login(loginDto)
        return ResponseEntity.ok(token)  // Return JWT token to frontend
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<String> {
        // Handle logout (clear client-side token)
        return ResponseEntity.ok("Logged out successfully")
    }
}
