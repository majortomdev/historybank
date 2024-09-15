@RestController
@RequestMapping("/api/urls")
class UrlController(
    private val urlService: UrlService
) {

    @PostMapping("/save")
    fun saveUrl(@RequestBody urlDto: UrlDto, principal: Principal): ResponseEntity<UrlObj> {
        val savedUrl = urlService.saveUrl(UrlObj(url = urlDto.url), principal.name)
        return ResponseEntity.ok(savedUrl)
    }

    @GetMapping
    fun getAllUrls(principal: Principal): ResponseEntity<List<UrlObj>> {
        val urls = urlService.getUrlsForUser(principal.name)
        return ResponseEntity.ok(urls)
    }

    @DeleteMapping("/{id}")
    fun deleteUrl(@PathVariable id: Int, principal: Principal): ResponseEntity<Void> {
        urlService.deleteUrl(id, principal.name)
        return ResponseEntity.noContent().build()
    }
}
