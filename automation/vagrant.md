# Vagrant

## Add multiple block devices (VDI)

```
v.customize ['storagectl', :id, '--name',  'SATA Controller', '--add', 'sata',  '--controller', 'IntelAhci', '--portcount', 4]

unless File.exist?("/home/jsosic/_temp/packages-data-disk.vdi")
  v.customize ['createhd', '--filename', "/home/jsosic/_temp/packages-data-disk.vdi", '--size', params['data_disk_size']]
end
unless File.exist?("/home/jsosic/_temp/packages-journal-disk.vdi")
  v.customize ['createhd', '--filename', "/home/jsosic/_temp/packages-journal-disk.vdi", '--size', params['journal_disk_size']]
end

v.customize ['storageattach', :id, '--storagectl', 'SATA Controller', '--port', 0, '--device', 0, '--type', 'hdd', '--medium', "/home/jsosic/_temp/packages-data-disk.vdi"]
v.customize ['storageattach', :id, '--storagectl', 'SATA Controller', '--port', 1, '--device', 0, '--type', 'hdd', '--medium', "/home/jsosic/_temp/packages-journal-disk.vdi"]
```
