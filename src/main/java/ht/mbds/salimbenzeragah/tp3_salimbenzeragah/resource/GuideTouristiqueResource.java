package ht.mbds.salimbenzeragah.tp3_salimbenzeragah.resource;

import ht.mbds.salimbenzeragah.tp3_salimbenzeragah.llm.LlmClientForGuideTouristique;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/guide")
public class GuideTouristiqueResource {

    @Inject
    private LlmClientForGuideTouristique llmClient;

    /**
     * Retourne les 2 principaux endroits à visiter et le prix moyen d'un repas
     * dans la devise du pays pour la ville ou le pays indiqué.
     *
     * @param villeOuPays le nom de la ville ou du pays recherché
     * @return une réponse HTTP contenant les informations touristiques au format JSON
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endroitsDeVisite(@PathParam("ville_ou_pays") String villeOuPays) {
        String reponseJson = llmClient.demanderInfos(villeOuPays);
        return Response.ok(reponseJson).build();
    }
}