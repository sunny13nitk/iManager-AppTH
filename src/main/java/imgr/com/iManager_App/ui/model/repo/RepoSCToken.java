package imgr.com.iManager_App.ui.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import imgr.com.iManager_App.ui.model.entity.TY_SCToken;

@Repository
public interface RepoSCToken extends JpaRepository<TY_SCToken, String>
{

}
