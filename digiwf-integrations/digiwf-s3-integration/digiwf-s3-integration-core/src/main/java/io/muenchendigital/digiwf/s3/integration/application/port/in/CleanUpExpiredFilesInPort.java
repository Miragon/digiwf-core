package io.muenchendigital.digiwf.s3.integration.application.port.in;

import org.springframework.transaction.annotation.Transactional;

public interface CleanUpExpiredFilesInPort {
  @Transactional
  void cleanUpExpiredFolders();
}
