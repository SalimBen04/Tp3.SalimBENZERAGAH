package ht.mbds.salimbenzeragah.tp3_salimbenzeragah.resource;

import ht.mbds.salimbenzeragah.tp3_salimbenzeragah.llm.LlmClientForGuideTouristique;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/guide")
public class GuideTouristiqueResource {

    @Inject
    private LlmClientForGuideTouristique llmClient;

    /**
     * Retourne les principaux endroits à visiter (2 par défaut) et le prix moyen d'un repas
     * dans la devise du pays pour la ville ou le pays indiqué.
     *
     * @param villeOuPays le nom de la ville ou du pays recherché
     * @param nb          le nombre d'endroits à visiter souhaité (2 par défaut)
     * @return une réponse HTTP contenant les informations touristiques au format JSON
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endroitsDeVisite(
            @PathParam("ville_ou_pays") String villeOuPays,
            @QueryParam("nb") @DefaultValue("2") int nb) {
        String reponseJson = llmClient.demanderInfos(villeOuPays, nb);
        return Response.ok(reponseJson).build();
    }
}