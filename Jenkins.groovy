node{

    stage("Clone Git"){
      git "git@github.com:SharifAbdulcoder/Jan_Pipeline.git"
    }

    stage("Install Requirements"){
      sh "scp -r * ec2-user@${ENV}:/tmp"
      sh "ssh ec2-user@${ENV} virtualenv /tmp/venv"
      sh "ssh ec2-user@${ENV} ./tmp/venv/activate"
      sh "ssh ec2-user@${ENV} sudo pip install -r /tmp/requirments.txt"

    }
    stage("Start python app") {
      sh "ssh ec2-user@${ENV} python /tmp/01-hello-world/hello.py"



    }
}
