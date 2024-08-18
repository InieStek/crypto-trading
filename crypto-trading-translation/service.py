import os
from typing import Dict, Any

from deep_translator import GoogleTranslator
from flask import Flask, request, jsonify
from py_eureka_client import eureka_client

app = Flask(__name__)

# Konfiguracja Eureka client
eureka_client.init(
  eureka_server=os.getenv("EUREKA_CLIENT_SERVICEURL_DEFAULTZONE",
                          "http://eureka-server:8761/eureka"),
  app_name="translation-service",
  instance_port=5000
)


def translate_text(text: str, src_lang: str, tgt_lang: str) -> str:
  try:
    translator = GoogleTranslator(source=src_lang, target=tgt_lang)
    translated = translator.translate(text)
    return translated
  except Exception as e:
    return f"Error: {e}"


@app.route('/translate', methods=['POST'])
def translate() -> Dict[str, Any]:
  data: Dict[str, Any] = request.json
  text: str = data.get('text', '')
  src_lang: str = data.get('src_lang', '')
  tgt_lang: str = data.get('tgt_lang', '')

  if not all([text, src_lang, tgt_lang]):
    return jsonify({"error": "Missing required parameters"}), 400

  translated: str = translate_text(text, src_lang, tgt_lang)
  return jsonify({"translated_text": translated})


if __name__ == '__main__':
  app.run(host='0.0.0.0', port=5000)
