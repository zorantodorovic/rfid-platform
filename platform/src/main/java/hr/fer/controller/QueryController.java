package hr.fer.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * kontroleri koji razgovara s vanjskom komponentom
 * npr ako treba spremiti podatak u bazu
 * npr ako treba vidjeti jel korisnikova baza ziva
 */
@RestControllerAdvice
@RequestMapping("/queries")
public class QueryController {
}
