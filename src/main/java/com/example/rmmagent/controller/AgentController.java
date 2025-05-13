package com.example.rmmagent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@GetMapping("/info")
public ResponseEntity<String> getInfo() {
	    return ResponseEntity.ok("RMM Agent v3.1 - Spring Boot App. Powered by Jenkins & Kubernetes.");
}
