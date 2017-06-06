/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.exceptions.IllegalOrphanException;
import Model.exceptions.NonexistentEntityException;
import Model.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author adilson_f
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getMensagemCollection() == null) {
            usuario.setMensagemCollection(new ArrayList<Mensagem>());
        }
        if (usuario.getMensagemCollection1() == null) {
            usuario.setMensagemCollection1(new ArrayList<Mensagem>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Mensagem> attachedMensagemCollection = new ArrayList<Mensagem>();
            for (Mensagem mensagemCollectionMensagemToAttach : usuario.getMensagemCollection()) {
                mensagemCollectionMensagemToAttach = em.getReference(mensagemCollectionMensagemToAttach.getClass(), mensagemCollectionMensagemToAttach.getMensagemPK());
                attachedMensagemCollection.add(mensagemCollectionMensagemToAttach);
            }
            usuario.setMensagemCollection(attachedMensagemCollection);
            Collection<Mensagem> attachedMensagemCollection1 = new ArrayList<Mensagem>();
            for (Mensagem mensagemCollection1MensagemToAttach : usuario.getMensagemCollection1()) {
                mensagemCollection1MensagemToAttach = em.getReference(mensagemCollection1MensagemToAttach.getClass(), mensagemCollection1MensagemToAttach.getMensagemPK());
                attachedMensagemCollection1.add(mensagemCollection1MensagemToAttach);
            }
            usuario.setMensagemCollection1(attachedMensagemCollection1);
            em.persist(usuario);
            for (Mensagem mensagemCollectionMensagem : usuario.getMensagemCollection()) {
                Usuario oldUsuarioOfMensagemCollectionMensagem = mensagemCollectionMensagem.getUsuario();
                mensagemCollectionMensagem.setUsuario(usuario);
                mensagemCollectionMensagem = em.merge(mensagemCollectionMensagem);
                if (oldUsuarioOfMensagemCollectionMensagem != null) {
                    oldUsuarioOfMensagemCollectionMensagem.getMensagemCollection().remove(mensagemCollectionMensagem);
                    oldUsuarioOfMensagemCollectionMensagem = em.merge(oldUsuarioOfMensagemCollectionMensagem);
                }
            }
            for (Mensagem mensagemCollection1Mensagem : usuario.getMensagemCollection1()) {
                Usuario oldUsuario1OfMensagemCollection1Mensagem = mensagemCollection1Mensagem.getUsuario1();
                mensagemCollection1Mensagem.setUsuario1(usuario);
                mensagemCollection1Mensagem = em.merge(mensagemCollection1Mensagem);
                if (oldUsuario1OfMensagemCollection1Mensagem != null) {
                    oldUsuario1OfMensagemCollection1Mensagem.getMensagemCollection1().remove(mensagemCollection1Mensagem);
                    oldUsuario1OfMensagemCollection1Mensagem = em.merge(oldUsuario1OfMensagemCollection1Mensagem);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getUsUsuario()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getUsUsuario());
            Collection<Mensagem> mensagemCollectionOld = persistentUsuario.getMensagemCollection();
            Collection<Mensagem> mensagemCollectionNew = usuario.getMensagemCollection();
            Collection<Mensagem> mensagemCollection1Old = persistentUsuario.getMensagemCollection1();
            Collection<Mensagem> mensagemCollection1New = usuario.getMensagemCollection1();
            List<String> illegalOrphanMessages = null;
            for (Mensagem mensagemCollectionOldMensagem : mensagemCollectionOld) {
                if (!mensagemCollectionNew.contains(mensagemCollectionOldMensagem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mensagem " + mensagemCollectionOldMensagem + " since its usuario field is not nullable.");
                }
            }
            for (Mensagem mensagemCollection1OldMensagem : mensagemCollection1Old) {
                if (!mensagemCollection1New.contains(mensagemCollection1OldMensagem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mensagem " + mensagemCollection1OldMensagem + " since its usuario1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Mensagem> attachedMensagemCollectionNew = new ArrayList<Mensagem>();
            for (Mensagem mensagemCollectionNewMensagemToAttach : mensagemCollectionNew) {
                mensagemCollectionNewMensagemToAttach = em.getReference(mensagemCollectionNewMensagemToAttach.getClass(), mensagemCollectionNewMensagemToAttach.getMensagemPK());
                attachedMensagemCollectionNew.add(mensagemCollectionNewMensagemToAttach);
            }
            mensagemCollectionNew = attachedMensagemCollectionNew;
            usuario.setMensagemCollection(mensagemCollectionNew);
            Collection<Mensagem> attachedMensagemCollection1New = new ArrayList<Mensagem>();
            for (Mensagem mensagemCollection1NewMensagemToAttach : mensagemCollection1New) {
                mensagemCollection1NewMensagemToAttach = em.getReference(mensagemCollection1NewMensagemToAttach.getClass(), mensagemCollection1NewMensagemToAttach.getMensagemPK());
                attachedMensagemCollection1New.add(mensagemCollection1NewMensagemToAttach);
            }
            mensagemCollection1New = attachedMensagemCollection1New;
            usuario.setMensagemCollection1(mensagemCollection1New);
            usuario = em.merge(usuario);
            for (Mensagem mensagemCollectionNewMensagem : mensagemCollectionNew) {
                if (!mensagemCollectionOld.contains(mensagemCollectionNewMensagem)) {
                    Usuario oldUsuarioOfMensagemCollectionNewMensagem = mensagemCollectionNewMensagem.getUsuario();
                    mensagemCollectionNewMensagem.setUsuario(usuario);
                    mensagemCollectionNewMensagem = em.merge(mensagemCollectionNewMensagem);
                    if (oldUsuarioOfMensagemCollectionNewMensagem != null && !oldUsuarioOfMensagemCollectionNewMensagem.equals(usuario)) {
                        oldUsuarioOfMensagemCollectionNewMensagem.getMensagemCollection().remove(mensagemCollectionNewMensagem);
                        oldUsuarioOfMensagemCollectionNewMensagem = em.merge(oldUsuarioOfMensagemCollectionNewMensagem);
                    }
                }
            }
            for (Mensagem mensagemCollection1NewMensagem : mensagemCollection1New) {
                if (!mensagemCollection1Old.contains(mensagemCollection1NewMensagem)) {
                    Usuario oldUsuario1OfMensagemCollection1NewMensagem = mensagemCollection1NewMensagem.getUsuario1();
                    mensagemCollection1NewMensagem.setUsuario1(usuario);
                    mensagemCollection1NewMensagem = em.merge(mensagemCollection1NewMensagem);
                    if (oldUsuario1OfMensagemCollection1NewMensagem != null && !oldUsuario1OfMensagemCollection1NewMensagem.equals(usuario)) {
                        oldUsuario1OfMensagemCollection1NewMensagem.getMensagemCollection1().remove(mensagemCollection1NewMensagem);
                        oldUsuario1OfMensagemCollection1NewMensagem = em.merge(oldUsuario1OfMensagemCollection1NewMensagem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getUsUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getUsUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Mensagem> mensagemCollectionOrphanCheck = usuario.getMensagemCollection();
            for (Mensagem mensagemCollectionOrphanCheckMensagem : mensagemCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Mensagem " + mensagemCollectionOrphanCheckMensagem + " in its mensagemCollection field has a non-nullable usuario field.");
            }
            Collection<Mensagem> mensagemCollection1OrphanCheck = usuario.getMensagemCollection1();
            for (Mensagem mensagemCollection1OrphanCheckMensagem : mensagemCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Mensagem " + mensagemCollection1OrphanCheckMensagem + " in its mensagemCollection1 field has a non-nullable usuario1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
