# Use Case Specification: Delete User Account

## Use Case ID: UC-005

### Purpose
This document describes the use case for deleting a user account from the system.

### Actors
- User
- System Administrator

### Pre-conditions
- User must be logged into the application.
- User must have permission to delete their account.

### Post-conditions
- User account is deleted from the system.
- User data is no longer accessible.

### Main Flow
1. User initiates the account deletion process.
2. System prompts the user to confirm the deletion.
3. User confirms the deletion.
4. System deletes the user account and all associated data.
5. System displays a confirmation message to the user.

### Alternate Flow
**If the user chooses to cancel the deletion:**  
- 3a. User cancels the deletion.  
- 3b. System returns to the main user dashboard.

### Notes
- Deleted accounts cannot be recovered. Ensure that the user is aware of this before proceeding with the deletion.