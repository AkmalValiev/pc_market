package uz.pdp.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pc_market.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {

    Optional<AttachmentContent> getByAttachment_Id(Integer attachment_id);

}
