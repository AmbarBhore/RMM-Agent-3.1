@GetMapping("/info")
public ResponseEntity<String> getInfo() {
	    return ResponseEntity.ok("RMM Agent v3.1 - Spring Boot App. Powered by Jenkins & Kubernetes.");
}
