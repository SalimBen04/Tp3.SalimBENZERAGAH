package ht.mbds.salimbenzeragah.tp3_salimbenzeragah.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/guide")
public class GuideTouristiqueResource {

    /**
     * Retourne les principaux endroits à visiter dans une ville ou un pays.
     *
     * @param villeOuPays le nom de la ville ou du pays recherché
     * @return un tableau JSON contenant le nom du lieu
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public String[] endroitsDeVisite(@PathParam("ville_ou_pays") String villeOuPays) {
        return new String[]{villeOuPays};
    }
}