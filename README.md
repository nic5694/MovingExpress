# MovingExpress
Full stack web application for a moving company

##### Branch Naming template
For branches to be created the template should be as follows
```
branchType/boardInitals-ticketId-name-of-the-story
```
Branch type options:
- feat -> feature
- bug -> bug
- doc -> documentation
Example:
```
feat/ME-876-showing-branch-naming-conventions
```
When creating the branch let JIRA create the base branch and just add on the type of branch you are doing, here is a tutorial
![image](https://github.com/nic5694/MovingExpress/assets/101201789/def6cb66-d9b7-46a1-a200-d023a546832f)
Once clicked you will see the following
![image](https://github.com/nic5694/MovingExpress/assets/101201789/00589089-3fc2-45ef-9885-acdd58ec8ef2)
CLick the clipboard button and paste it in your terminal or gitbash and add on the branch tag -> meaning the type of branch feat or bug
#### Pull Request Commit Naming
This is pretty much the exact same as the Pull Request Naming except that at the end there will be an auto-generated number in parentheses. Please don't delete it. Simply add your stuff before it.
```
//put whatever type of branch you were on if it was a bug put bug if it was a feature put feat like explained above
feat(ME-JiraID): short description (#420)
```
#### SSH Keys

Note: This section is if you are using SSH keys to clone your repo and have already generated your GitHub SSH keys and ran your keygen in your terminal (console of your computer)

A common problem you face when setting the SSH key on the Mac is that when you restart your computer the ssh key makes you authenticate with your passphrase. To set the password and not make it ask for your passphrase everytime you run a command needing permissions. Run the command:
```bash
ssh-add
```
and enter your passphrase when prompted (Note: if you are not using the default filename, you'll need to specify your key filename ssh-add ~/.ssh/myprivatekeyname). The system will print Identity Added if successful.
