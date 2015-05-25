package model.classes;

import java.io.Serializable;
import java.util.List;

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
import model.exceptions.ControlledException;
import model.exceptions.EqualPlayersException;
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
    
    public void edit(IPlayer player) throws ControlledException{
        EntityManager em = null;
        try {
        	/*salvando o player*/
            em = getEntityManager();
            em.getTransaction().begin();
            player = em.merge(player);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
        	/*descobrindo qual foi o erro e tratando a excecao*/
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = player.getId();
                if (getPlayer(id) == null) {
                    throw new NonexistentEntityException("Monstro, o player ainda nao existe.\n"
                    									+ "Nao eh possivel edita-lo");
                }else{
                	throw new ControlledException("Monstro, ocorreu um erro ao salvar o player.\n",ex);
                }
            }else{
            	throw new ControlledException("Monstro, ocorreu um erro ao salvar o player.\n"
            							  +ex.getMessage(), ex);
            }
        } finally {
        	/*fechando o entityManager*/
            if (em != null) {
                em.close();
            }
        }
    }
    public boolean possibleName(String name){
    	/*verifica se o nome nao esta entre os nomes ja utilizados*/
    	return !(getPossiblePlayers().contains(name));
    }
    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Player player;
            try {
            	/*recupera o player*/
                player = em.getReference(Player.class, id);
                player.getId();
            } catch (EntityNotFoundException enfe) {
            	/*informa que o player nao existe*/
                throw new NonexistentEntityException("Monstro, esse player n�o existe.\n"
                									+ "N�o � poss�vel exclu�-lo.", enfe);
            }
            /*remove o player*/
            em.remove(player);
            em.getTransaction().commit();
        } finally {
        	/*fechando o entityManager*/
            if (em != null) {
                em.close();
            }
        }
    }
    public IPlayer getPlayer(int id) {
		EntityManager em = getEntityManager();
        try {
        	/*procura o player*/
            return em.find(Player.class, id);
        } finally {
            em.close();
        }
	}	
	public IPlayer getPlayer(String name)  throws ControlledException {
		EntityManager em = getEntityManager();
        try {
        	/*procura o player*/
        	Query query = em.createQuery("select p from Player p where p.name = :name", 
        								 Player.class);
            query.setParameter("name", name);
            return (IPlayer) query.getSingleResult();            
        } catch (NoResultException enfe) {
        	/*Erro: player nao encontrado*/
            throw new NonexistentEntityException("Monstro, n�o existe jogador com esse nome.\n"
            									  + "N�o � poss�vel carreg�-lo", enfe);
        } catch (NonUniqueResultException e) {
        	/*Erro: mais de um player com esse nome*/
        	throw new EqualPlayersException("Monstro, o frango que fez o jogo\n"
					  				+ "deixou voce criar mais de um player\n"
					  				+ "com o mesmo nome e agora n�o sabe qual carregar.", e);
		}finally {
            em.close();
        }
	}
	
	public void savePlayer(Object player) throws ControlledException {
		EntityManager em = null;
        try {
        	/*Salva o player*/
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(player);
            em.getTransaction().commit();
        } catch (EntityExistsException e){
        	/*Erro: player ja existia*/
        	throw new PreexistingEntityException("Monstro, esse player j� existe,\n"
        										 + "n�o � possivel cri�-lo novamente",e);
        } catch(PersistenceException ex){
        	/*Erro desconhecido*/
        	String message="";
    		if(ex.getMessage()!=null)
    			message=ex.getMessage();
        	throw new ControlledException("Monstro, ocorreu um erro ao salvar o player.\n"
					  					  +message, ex);
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
            /*Verifica qual tipo de elemento eh desejado*/
            if(type.equalsIgnoreCase("Item"))
            	cq.select(cq.from(Item.class));
            else
            	cq.select(cq.from(Pokemonstro.class));
            TypedQuery<Element> q = em.createQuery(cq);
            /*recupera os elementos*/
            return (IElement[]) q.getResultList().toArray(new IElement[0]);
        } finally {
            em.close();
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getPossiblePlayers(){
		EntityManager em = getEntityManager();
		/*Recupera o nome de todos os players*/
		Query query = em.createQuery("select p.name from Player p", Player.class);		 
        return (List<String>) query.getResultList();
	}
}
