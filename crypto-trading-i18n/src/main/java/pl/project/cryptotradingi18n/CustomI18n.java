package pl.project.cryptotradingi18n;

import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomI18n {
  private final RestTemplate restTemplate;
  private final String translationServiceUrl;
  private final String defaultLanguage;
  private final Map<String, Map<String, String>> translations;
  private final boolean useCaching;

  public CustomI18n(RestTemplate restTemplate, String translationServiceUrl, String defaultLanguage, boolean useCaching) {
    this.restTemplate = restTemplate;
    this.translationServiceUrl = translationServiceUrl;
    this.defaultLanguage = defaultLanguage;
    this.useCaching = useCaching;
    this.translations = useCaching ? new ConcurrentHashMap<>() : null;
  }

  public String translate(String key, String targetLanguage) {
    if (targetLanguage.equals(defaultLanguage)) {
      return key;
    }

    if (useCaching) {
      Map<String, String> langTranslations = translations.computeIfAbsent(targetLanguage, k -> new HashMap<>());
      return langTranslations.computeIfAbsent(key, k -> callTranslationService(key, defaultLanguage, targetLanguage));
    } else {
      return callTranslationService(key, defaultLanguage, targetLanguage);
    }
  }

  private String callTranslationService(String text, String srcLang, String tgtLang) {
    Map<String, String> body = new HashMap<>();
    body.put("text", text);
    body.put("src_lang", srcLang);
    body.put("tgt_lang", tgtLang);

    try {
      Map<String, String> response = restTemplate.postForObject(translationServiceUrl + "/translate", body, Map.class);
      return response != null ? response.get("translated_text") : text;
    } catch (Exception e) {
      e.printStackTrace();
      return text;
    }
  }

  public void clearCache(String language) {
    if (useCaching && translations != null) {
      translations.remove(language);
    }
  }

  public void clearAllCache() {
    if (useCaching && translations != null) {
      translations.clear();
    }
  }
}