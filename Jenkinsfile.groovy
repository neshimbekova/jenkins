node{
    properties([parameters([string(defaultValue: 'IP', description: 'Where to build e.g IP', name: "ENV", trim: true)])])
    stage("Clone Git"){
      git "git@github.com:SharifAbdulcoder/Jan_Pipeline.git"
    }

    stage("Install Requirements"){
      sh "ssh ec2-user@${ENV} sudo yum install python-pip -y"
      sh "ssh ec2-user@${ENV} sudo pip install flask"
      sh "ssh ec2-user@${ENV} sudo pip install virtualenv"
      sh "scp -r * ec2-user@${ENV}:/tmp"
      sh "ssh ec2-user@${ENV} virtualenv /tmp/venv"
      sh "ssh ec2-user@${ENV} ./tmp/venv/bin/activate"
      sh "ssh ec2-user@${ENV} sudo pip install -r /tmp/requirments.txt"

    }
    stage("Start python app") {
      sh "ssh ec2-user@${ENV} python /tmp/01-hello-world/hello.py"

    }
}
