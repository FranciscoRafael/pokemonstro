package model.classes;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import model.interfaces.IElement;
import model.interfaces.IPlayer;
import model.interfaces.IStorage;
import model.classes.Player;
import model.classes.element.*;
import model.exceptions.NonexistentEntityException;
import model.exceptions.PreexistingEntityException;
import anima.annotation.Component;
import anima.component.base.ComponentBase;

import javax.imageio.ImageIO;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
*
* @author Danilo Charantola
*/

@Component(id="<pokemonstro.src.model.classes.Storage>",
		   provides={"<pokemonstro.src.model.interfaces.IStorage>"})
public class Storage extends ComponentBase implements IStorage, Serializable{	
	private static final long serialVersionUID = 1L;
	/**
	 * Uso de Singleton
	 */	
	Storage instance=null;
	public Storage getInstance(){
		if(instance!=null)
			instance=new Storage();
		return instance;
	}
	private Storage() {}
	
	private EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokemonstroPU");
        return emf.createEntityManager();
    }
    
    public void edit(Player player) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            player = em.merge(player);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = player.getId();
                if (getPlayer(id) == null) {
                    throw new NonexistentEntityException("The player with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Player player;
            try {
                player = em.getReference(Player.class, id);
                player.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("Tentou remover jogador inexistente. Nao existe jogador com o id " + id , enfe);
            }
            em.remove(player);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    	
	public IPlayer getPlayer(int id)  throws NonexistentEntityException {
		EntityManager em = getEntityManager();
        try {
            return em.find(Player.class, id);
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("Tentou remover jogador inexistente. Nao existe jogador com o id " + id , enfe);
        }finally {
            em.close();
        }
	}
	
	public static Image getImage(String path){
		String DIRETORIO_RELATIVO = "../arquivos";
		String diretorio = Storage.class.getResource(DIRETORIO_RELATIVO).getPath();

		File diretorioRaiz = new File(diretorio + "/" + path);
		Image imagem=null;
		try{
			imagem = ImageIO.read(diretorioRaiz);
		}catch (IOException erro){
			erro.printStackTrace();
		}		
		return imagem;
	}

	public void savePlayer(IPlayer player) throws PreexistingEntityException, Exception {
		EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(player);
            em.getTransaction().commit();
        } catch (EntityExistsException e){
        	throw new PreexistingEntityException("Ja existe um jogador com este identificador",e);
        } catch(Exception e){
        	throw e;
        }finally {
            if (em != null) {
                em.close();
            }
        }
	}

	public IElement[] getAllElements(String type) {
		EntityManager em = getEntityManager();
        try {
        	CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Element> cq = cb.createQuery(Element.class);
            if(type.equalsIgnoreCase("Item"))
            	cq.select(cq.from(Item.class));
            else
            	cq.select(cq.from(Pokemonstro.class));
            Query q = em.createQuery(cq);
            return (IElement[]) q.getResultList().toArray(new IElement[0]);
        } finally {
            em.close();
        }
	}
}
