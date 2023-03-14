package uz.pdp.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pc_market.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
