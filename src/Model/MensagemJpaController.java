/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.exceptions.NonexistentEntityException;
import Model.exceptions.PreexistingEntityException;
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
 * @author adilson_f
 */
public class MensagemJpaController implements Serializable {

    public MensagemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mensagem mensagem) throws PreexistingEntityException, Exception {
        if (mensagem.getMensagemPK() == null) {
            mensagem.setMensagemPK(new MensagemPK());
        }
        mensagem.getMensagemPK().setMsUsDestino(mensagem.getUsuario().getUsUsuario());
        mensagem.getMensagemPK().setMsUsOrigem(mensagem.getUsuario1().getUsUsuario());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = mensagem.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getUsUsuario());
                mensagem.setUsuario(usuario);
            }
            Usuario usuario1 = mensagem.getUsuario1();
            if (usuario1 != null) {
                usuario1 = em.getReference(usuario1.getClass(), usuario1.getUsUsuario());
                mensagem.setUsuario1(usuario1);
            }
            em.persist(mensagem);
            if (usuario != null) {
                usuario.getMensagemCollection().add(mensagem);
                usuario = em.merge(usuario);
            }
            if (usuario1 != null) {
                usuario1.getMensagemCollection().add(mensagem);
                usuario1 = em.merge(usuario1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMensagem(mensagem.getMensagemPK()) != null) {
                throw new PreexistingEntityException("Mensagem " + mensagem + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mensagem mensagem) throws NonexistentEntityException, Exception {
        mensagem.getMensagemPK().setMsUsDestino(mensagem.getUsuario().getUsUsuario());
        mensagem.getMensagemPK().setMsUsOrigem(mensagem.getUsuario1().getUsUsuario());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mensagem persistentMensagem = em.find(Mensagem.class, mensagem.getMensagemPK());
            Usuario usuarioOld = persistentMensagem.getUsuario();
            Usuario usuarioNew = mensagem.getUsuario();
            Usuario usuario1Old = persistentMensagem.getUsuario1();
            Usuario usuario1New = mensagem.getUsuario1();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getUsUsuario());
                mensagem.setUsuario(usuarioNew);
            }
            if (usuario1New != null) {
                usuario1New = em.getReference(usuario1New.getClass(), usuario1New.getUsUsuario());
                mensagem.setUsuario1(usuario1New);
            }
            mensagem = em.merge(mensagem);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getMensagemCollection().remove(mensagem);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getMensagemCollection().add(mensagem);
                usuarioNew = em.merge(usuarioNew);
            }
            if (usuario1Old != null && !usuario1Old.equals(usuario1New)) {
                usuario1Old.getMensagemCollection().remove(mensagem);
                usuario1Old = em.merge(usuario1Old);
            }
            if (usuario1New != null && !usuario1New.equals(usuario1Old)) {
                usuario1New.getMensagemCollection().add(mensagem);
                usuario1New = em.merge(usuario1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MensagemPK id = mensagem.getMensagemPK();
                if (findMensagem(id) == null) {
                    throw new NonexistentEntityException("The mensagem with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MensagemPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mensagem mensagem;
            try {
                mensagem = em.getReference(Mensagem.class, id);
                mensagem.getMensagemPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mensagem with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = mensagem.getUsuario();
            if (usuario != null) {
                usuario.getMensagemCollection().remove(mensagem);
                usuario = em.merge(usuario);
            }
            Usuario usuario1 = mensagem.getUsuario1();
            if (usuario1 != null) {
                usuario1.getMensagemCollection().remove(mensagem);
                usuario1 = em.merge(usuario1);
            }
            em.remove(mensagem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mensagem> findMensagemEntities() {
        return findMensagemEntities(true, -1, -1);
    }

    public List<Mensagem> findMensagemEntities(int maxResults, int firstResult) {
        return findMensagemEntities(false, maxResults, firstResult);
    }

    private List<Mensagem> findMensagemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mensagem.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Mensagem findMensagem(MensagemPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mensagem.class, id);
        } finally {
            em.close();
        }
    }

    public int getMensagemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mensagem> rt = cq.from(Mensagem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
