@RestController
@RequestMapping("/api")
class TestController {

    @GetMapping("/test")
    fun hello(): String {
        return "Hello, Kotlin + Spring Boot!"
    }
}
