/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appriver.program.pkgfinal;

import appriver.program.pkgfinal.exceptions.NonexistentEntityException;
import appriver.program.pkgfinal.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Squidward5
 */
public class InformationJpaController implements Serializable
{

    public InformationJpaController(EntityManagerFactory emf)
    {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager()
    {
        try
        {
        return emf.createEntityManager();
        }catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
        return null;
    }

    public void create(Information information) throws PreexistingEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(information);
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            if (findInformation(information.getUniquemsgid()) != null)
            {
                throw new PreexistingEntityException("Information " + information + " already exists.", ex);
            }
            throw ex;
        } finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public void edit(Information information) throws NonexistentEntityException, Exception
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            information = em.merge(information);
            em.getTransaction().commit();
        } catch (Exception ex)
        {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0)
            {
                String id = information.getUniquemsgid();
                if (findInformation(id) == null)
                {
                    throw new NonexistentEntityException("The information with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException
    {
        EntityManager em = null;
        try
        {
            em = getEntityManager();
            em.getTransaction().begin();
            Information information;
            try
            {
                information = em.getReference(Information.class, id);
                information.getUniquemsgid();
            } catch (EntityNotFoundException enfe)
            {
                throw new NonexistentEntityException("The information with id " + id + " no longer exists.", enfe);
            }
            em.remove(information);
            em.getTransaction().commit();
        } finally
        {
            if (em != null)
            {
                em.close();
            }
        }
    }

    public List<Information> findInformationEntities()
    {
        return findInformationEntities(true, -1, -1);
    }

    public List<Information> findInformationEntities(int maxResults, int firstResult)
    {
        return findInformationEntities(false, maxResults, firstResult);
    }

    private List<Information> findInformationEntities(boolean all, int maxResults, int firstResult)
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Information.class));
            Query q = em.createQuery(cq);
            if (!all)
            {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally
        {
            em.close();
        }
    }

    public Information findInformation(String id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(Information.class, id);
        } finally
        {
            em.close();
        }
    }

    public int getInformationCount()
    {
        EntityManager em = getEntityManager();
        try
        {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Information> rt = cq.from(Information.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally
        {
            em.close();
        }
    }

    Information findInformation()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
