package mvcModel;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Dept;
import entities.Pending;
import entities.Profile;
import entities.User;

/**
 * Session Bean implementation class MemberService
 */
@Stateless
@LocalBean
public class MemberService {

	@PersistenceContext(unitName="JEEgotm")
	private EntityManager em;
	
	
	
    public MemberService() {
        // TODO Auto-generated constructor stub
    }
    
    public List<User> getAllUsers()
    {
    List<User> users = new ArrayList<User>();
    TypedQuery<User> query = em.createNamedQuery("User.findAll",User.class);
    users = query.getResultList();
    return users;
    }
    
    public List<Pending> getAllPending() {
        EntityManager em = Persistence.createEntityManagerFactory("JEEgotm").createEntityManager();
        try {
            TypedQuery<Pending> query = em.createQuery("SELECT p FROM Pending p", Pending.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    
    public String addUser(User user, Profile profile, String deptna) {
    	EntityManager em = Persistence.createEntityManagerFactory("JEEgotm").createEntityManager();
    	EntityTransaction transaction = em.getTransaction();
    	try {
            transaction.begin();
            
            TypedQuery<Dept> deptQuery = em.createQuery("SELECT d FROM Dept d WHERE d.deptna = :deptna", Dept.class);
            deptQuery.setParameter("deptna", deptna);
            Dept dept = deptQuery.getSingleResult();

            // Set the properties of the User object
            user.setDept(dept);
            user.setProfile(profile);

            // Persist both objects to the database
            em.persist(profile);
            em.persist(user);
            
            transaction.commit();
            
            return "User added successfully";
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public String addPending(Pending user) {
        EntityManager em = Persistence.createEntityManagerFactory("JEEgotm").createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            // Check if a pending request already exists for the given username
            TypedQuery<Pending> query = em.createQuery("SELECT p FROM Pending p WHERE p.username = :username", Pending.class);
            query.setParameter("username", user.getUsername());
            List<Pending> results = query.getResultList();
            if (!results.isEmpty()) {
                return "A pending request already exists for this user";
            }
            
            em.persist(user);
            
            transaction.commit();
            
            return "User added successfully";
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    
    public User getUserById(int id) {
        EntityManager em = Persistence.createEntityManagerFactory("JEEgotm").createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        User user = null;
        try {
            transaction.begin();
            user = em.find(User.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return user;
    }
    
    public void updateUser(int id, String name, String discordTag, String email, String phoneN, int score, String deptna, Profile profile) {
        EntityManager em = Persistence.createEntityManagerFactory("JEEgotm").createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            TypedQuery<Dept> deptQuery = em.createQuery("SELECT d FROM Dept d WHERE d.deptna = :deptna", Dept.class);
            deptQuery.setParameter("deptna", deptna);
            Dept dept = deptQuery.getSingleResult();

            User user = em.find(User.class, id);
            user.setName(name);
            user.setDiscordTag(discordTag);
            user.setEmail(email);
            user.setPhoneN(phoneN);
            user.setScore(score);
            user.setDept(dept);
            
            Profile profileUser = em.find(Profile.class, profile.getIdprofile());
            profileUser.setIsAdmin(profile.getIsAdmin());
            profileUser.setPassword(profile.getPassword());
            profileUser.setUsername(profile.getUsername());
            
            

            em.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public String deleteUser(int id) {
        EntityManager em = Persistence.createEntityManagerFactory("JEEgotm").createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            User user = em.find(User.class, id);
            Profile profile = user.getProfile();
            em.remove(user);
            em.remove(profile);
            transaction.commit();
            
            return "User deleted successfully";
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public User getUserByUsername(String username) {
        EntityManager em = Persistence.createEntityManagerFactory("JEEgotm").createEntityManager();
        User user = null;
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.profile.username = :username", User.class);
            query.setParameter("username", username);
            user = query.getSingleResult();
        } catch (NoResultException e) {
            // Handle the case where no user was found with the given username
        } finally {
            em.close();
        }
        return user;
    }
    
    public List<User> getUsersWithoutProfile() {
        EntityManager em = Persistence.createEntityManagerFactory("JEEgotm").createEntityManager();
        List<User> users = null;
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.profile is null", User.class);
            users = query.getResultList();
        } catch (NoResultException e) {
            // Handle the case where no users were found without a profile
        } finally {
            em.close();
        }
        return users;
    }
    
    public void promoteUsers(String[] selectedUserIds, String deptna) {
        EntityManager em = Persistence.createEntityManagerFactory("JEEgotm").createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            // Retrieve all the users from the Pending table
            for (String userId : selectedUserIds) {
            	int id = Integer.parseInt(userId);
            	TypedQuery<Pending> query = em.createQuery("SELECT p FROM Pending p WHERE p.idpending = :id", Pending.class);
            	query.setParameter("id", id);
            	Pending pendingUser = query.getSingleResult();
            	System.out.println(pendingUser.getDiscordTag());


                User user = new User();
                user.setName(pendingUser.getName());
                user.setDiscordTag(pendingUser.getDiscordTag());
                user.setEmail(pendingUser.getEmail());
                user.setPhoneN(pendingUser.getPhoneN());
                
                TypedQuery<Dept> deptQuery = em.createQuery("SELECT d FROM Dept d WHERE d.deptna = :deptna", Dept.class);
                deptQuery.setParameter("deptna", deptna);
                Dept dept = deptQuery.getSingleResult();

                // Create a new Profile object for the user
                Profile profile = new Profile();
                profile.setUsername(pendingUser.getUsername());
                profile.setPassword(pendingUser.getPassword());

                
                // Set the user and profile relationships
                user.setProfile(profile);
                user.setDept(dept);

                // Save the user and profile to the database
                em.persist(profile);
                em.persist(user);
                

                // Remove the user from the Pending table
                em.remove(pendingUser);
                
            }

            transaction.commit();
                    
            }
            catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                throw e;
            } finally {
                em.close();
            }
    }
    

    
    public String deletePending(int id) {
        EntityManager em = Persistence.createEntityManagerFactory("JEEgotm").createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Pending p = em.find(Pending.class, id);
            
            em.remove(p);
            transaction.commit();
            
            return "Pending deleted successfully";
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
        
            




}
