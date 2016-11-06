package hr.fer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * kontroler koji razgovara s vanjskom komponentom
 * npr. ako korisnik zeli pingati senzor da vidi jel ziv preko njega to radi
 */
@RestControllerAdvice
@RequestMapping("/iot")
public class IoTController {
}
