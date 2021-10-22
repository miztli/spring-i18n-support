package com.example.spring.i18nsamples;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {

    @Resource
    private MessageSource messageSource;

    @GetMapping("/{lang}")
    public Map<String, String> localizedMessages(@PathVariable(name = "lang") final String lang,
                                                 @RequestParam(name = "name") final String name) {
        final Locale locale = Locale.forLanguageTag(lang);
        final String code = messageSource.getMessage("application.user.greetings.code", null, locale);
        final String message = messageSource.getMessage("application.user.greetings.message", new String[]{ name }, locale);

        return Map.of(
                "error", code,
                "message", message
        );
    }
}
