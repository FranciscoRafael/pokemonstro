package model.classes;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import model.classes.element.Element;
import model.classes.element.Item;
import model.classes.element.Pokemonstro;
import model.exceptions.EqualPlayers;
import model.exceptions.NonexistentEntityException;
import model.exceptions.PreexistingEntityException;
import model.interfaces.IElement;
import model.interfaces.IPlayer;
import model.interfaces.IStorage;

import anima.annotation.Component;
import anima.component.base.ComponentBase;

/**
*
* @author Danilo Charantola
*/

@Component(id="<pokemonstro.src.model.classes.Storage>",
		   provides={"<pokemonstro.src.model.interfaces.IStorage>"})
public class Storage extends ComponentBase implements IStorage, Serializable{	
	private static final long serialVersionUID = 1L;
	
	private EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pokemonstroPU");
        return emf.createEntityManager();
    }
    
    public void edit(Player player) throws NonexistentEntityException, PersistenceException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            player = em.merge(player);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = player.getId();
                if (getPlayer(id) == null) {
                    throw new NonexistentEntityException("Monstro, o player ainda não existe.\n"
                    									+ "Não eh possivel editá-lo");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public boolean possibleName(String name){
    	return !(getPossiblePlayers().contains(name));
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
                throw new NonexistentEntityException("Monstro, esse player não existe.\n"
                									+ "Não é possível excluí-lo.", enfe);
            }
            em.remove(player);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public IPlayer getPlayer(int id) {
		EntityManager em = getEntityManager();
        try {
            return em.find(Player.class, id);
        } finally {
            em.close();
        }
	}	
	public IPlayer getPlayer(String name)  throws NonexistentEntityException, EqualPlayers {
		EntityManager em = getEntityManager();
        try {
        	Query query = em.createQuery("select p from Player p where p.name = :name", 
        								 Player.class);
            query.setParameter("name", name);
            return (IPlayer) query.getSingleResult();            
        } catch (NoResultException enfe) {
            throw new NonexistentEntityException("Monstro, não existe jogador com esse nome.\n"
            									  + "Não é possível carregá-lo", enfe);
        } catch (NonUniqueResultException e) {
        	throw new EqualPlayers("Monstro, o frango que fez o jogo\n"
					  				+ "deixou voce criar mais de um player\n"
					  				+ "com o mesmo nome e agora não sabe qual carregar.", e);
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

	public void savePlayer(IPlayer player) throws PreexistingEntityException, PersistenceException {
		EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(player);
            em.getTransaction().commit();
        } catch (EntityExistsException e){
        	throw new PreexistingEntityException("Monstro, esse player já existe,\n"
        										 + "não é possivel criá-lo novamente",e);
        } catch(PersistenceException e){
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
            TypedQuery<Element> q = em.createQuery(cq);
            return (IElement[]) q.getResultList().toArray(new IElement[0]);
        } finally {
            em.close();
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getPossiblePlayers(){
		EntityManager em = getEntityManager();
		Query query = em.createQuery("select p.name from Player p", Player.class);		 
        return (List<String>) query.getResultList();
	}
}
