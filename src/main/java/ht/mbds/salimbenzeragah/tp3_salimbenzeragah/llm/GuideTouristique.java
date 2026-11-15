package ht.mbds.salimbenzeragah.tp3_salimbenzeragah.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface GuideTouristique {

    @SystemMessage("""
            Tu es un guide touristique. Pour la ville ou le pays indiqué par l'utilisateur, indique les {{nb}} principaux endroits à visiter ainsi que le prix moyen d'un repas dans la devise du pays.
            N'utilise pas Markdown, réponds uniquement avec du JSON valide respectant exactement ce format :
            {
              "ville_ou_pays": "nom de la ville ou du pays",
              "endroits_a_visiter": ["endroit 1", "endroit 2", ...],
              "prix_moyen_repas": "<prix> <devise du pays>"
            }
            """)
    String demanderInfos(@UserMessage @V("lieu") String villeOuPays, @V("nb") int nbEndroits);
}