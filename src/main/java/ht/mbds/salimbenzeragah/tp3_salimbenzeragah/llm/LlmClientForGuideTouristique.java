package ht.mbds.salimbenzeragah.tp3_salimbenzeragah.llm;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.Dependent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

@Dependent
public class LlmClientForGuideTouristique implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private transient GuideTouristique guideTouristique;

    public LlmClientForGuideTouristique() {
        initialiserAssistant();
    }

    private void initialiserAssistant() {
        String apiKey = System.getenv("GEMINI_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            apiKey = System.getProperty("GEMINI_KEY");
        }

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "La variable GEMINI_KEY est absente des variables d'environnement et des propriétés JVM."
            );
        }

        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-3.5-flash-lite")
                .responseFormat(ResponseFormat.JSON)
                .temperature(0.3)
                .build();

        guideTouristique = AiServices.builder(GuideTouristique.class)
                .chatModel(model)
                .build();
    }

    public String demanderInfos(String villeOuPays, int nbEndroits) {
        if (villeOuPays == null || villeOuPays.isBlank()) {
            throw new IllegalArgumentException("La ville ou le pays ne peut pas être vide.");
        }
        if (nbEndroits <= 0) {
            throw new IllegalArgumentException("Le nombre d'endroits à visiter doit être supérieur à 0.");
        }
        if (guideTouristique == null) {
            initialiserAssistant();
        }
        return guideTouristique.demanderInfos(villeOuPays, nbEndroits);
    }

    @Serial
    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        input.defaultReadObject();
        initialiserAssistant();
    }
}