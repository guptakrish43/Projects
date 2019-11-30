#include<bits/stdc++.h>
using namespace std;

char encode(char a,char b)
	{
		char c;
		c=(char)((int(a)+(int)b)%256);
		return c;
	}	

string encrypt(string msg,string key)
	{
		string res="";
		for(int i=0;i<msg.size();i++)	
			res+=encode(msg[i],key[i%key.size()]);
		return res;
	}

char decode(char a,char b)
	{
		char c;
		c=((int)a>(int)b)?(char)((int)a-(int)b):(char)((256+(int)a)-(int)b);
		return c;		
	}

string decrypt(string msg,string key)
	{
		string res="";
		for(int i=0;i<msg.size();i++)	
			res+=decode(msg[i],key[i%key.size()]);
		return res;
	}
	
bool validate(string q)
	{
		fstream fin;
		string s;
		fin.open("links.txt",ios::in);
		while(fin)	
			{
				getline(fin,s);			
				if(s.find(q)!=string::npos)
					{fin.close();					
					return true;}
			}	
		return false;	
	}	

void userdelete()
	{
		string name,s;
		cout<<"Enter User Name\n";	
		cin>>name;	
		if(validate(name))		
			{	//cout<<"a\n";
				fstream fio,ft;
				fio.open("links.txt",ios::in);
				ft.open("copy.txt",ios::app);
				while(getline(fio,s))
					{	
						if(s.find(name)!=string::npos)					
							continue;
						else
							ft<<s<<endl;
					}
				remove("links.txt");
				rename("copy.txt","links.txt");
				fio.close();
				ft.close();
			}
		else
			cout<<"Name not Exists\n";
	}		

void update()
	{
    		string name,s,pass;
		cout<<"Enter User Name\n";	
		cin>>name;	
		if(validate(name))		
			{	//cout<<"a\n";
				cout<<"Enter New Password\n";
				cin>>pass;
				fstream fio,ft;
				fio.open("links.txt",ios::in);
				ft.open("copy.txt",ios::app);
				while(getline(fio,s))
					{	
						if(s.find(name)!=string::npos)					
							ft<<name+" "+pass<<endl;	
						else
							ft<<s<<endl;
					}
				remove("links.txt");
				rename("copy.txt","links.txt");
				fio.close();
				ft.close();
			}
		else
			cout<<"Name not Exists\n";
	}

bool canregister(string q)
	{
		fstream fin;
		string s;
		fin.open("links.txt",ios::in);
		while(fin)	
			{
				getline(fin,s);			
				if(s.find(q)!=string::npos)
					{fin.close();					
					return false;}
			}	
		return true;

	 }	


string fetch(string q)	
	{
		fstream fio;
		string s;
		fio.open("links.txt",ios::in);		
		while(fio)
		 {
			getline(fio,s);
			if(s.find(q)!=string::npos)
			   {	string ns="";
				reverse(s.begin(),s.end());
				for(int i=0;i<s.size();i++)
				{	if(s[i]==' ')					
						break;
					else
						ns+=s[i];
				}			   
				return ns;			   
			    }										
		}	

	}


void Register()
	{
		string name,pass;
		cout<<"Enter Name\n";	
		cin>>name;
		if(canregister(name))
		{
		cout<<"Enter Password\n";
		cin>>pass;	
		if(canregister(pass)){
		fstream fio;
		string s=name+" "+pass;
		fio.open("links.txt",ios::app);
		fio<<s<<endl;	
		fio.close();}
		else
			cout<<"Password Exists\n";
		 }		
		else
			cout<<"User Exists\n";
	}

void clientsend()
	{						//ofstream to write ifstream to read
		string sen,rec,msg;		
		do{	
		cout<<"Enter Sender name(-1 for break):\n";		
		cin>>sen;
		if(sen=="-1")
			break;
		if(validate(sen)){	
		cout<<"Enter Receiver name\n";
		cin>>rec;
		cin.ignore();	
		if(validate(rec)){						
		cout<<"Enter Message:\n";
		getline(cin,msg);
		string Akey,Bkey;
		Akey=fetch(sen);
		Bkey=fetch(rec);	
		string enc1=encrypt(msg,Akey);	
		cout<<enc1<<"-->A to B\n";	
		string enc2=encrypt(enc1,Bkey);
		cout<<enc2<<"-->B to A\n";
		string dec1=decrypt(enc2,Akey);
		cout<<dec1<<"-->A to B\n";
		string dec2=decrypt(dec1,Bkey);
		cout<<dec2<<"\n";
			}
		else
		   cout<<"Invalid Receiver:\n";				
		}
	   else
		cout<<"Invalid Sender:\n";
		}while(true);
		
	}

void client()
	{
		int ch,flag=0;
		do{
			cout<<"1.Send Message\t2.Register\t3.Exit\n";cin>>ch;
			switch(ch)
			{
			case 1:clientsend();
				break;
			case 2:Register();
				break;
			default:flag=1;
				break;
			}	
			if(flag==1)
				break;
		}while(1);	
	}


void developer()
	{
		fstream fio;string s;int ch;int flag=0;
		do{
		cout<<"1.Insert User\t2.Check User\t3.Delete User\t4.Update userP\t5.Exit\n";cin>>ch;cin.ignore();
		switch(ch){
		case 1:	Register();
			break;
		case 2:	fio.open("links.txt",ios::in);
			fio.seekg(0,ios::beg);	
			while(fio)
			{
				getline(fio,s);
				cout<<s<<"\n";	
			}
			fio.close();
			break;
		case 3:	userdelete();
			break;
		case 4:	update();
			break;
		default:flag=1;
			break;
		   }
			if(flag==1)break;
		}while(1);
	}		
int main()
 {	
	int ch,flag=0;	
	do{	
	cout<<"1.Client\t2.Developer\t3.Exit\n";
	cin>>ch;
	switch(ch){
	case 1:	client();
		break;
	case 2:	developer();
		break;
	default:flag=1;
		break;
		}
	if(flag==1)
		break;
	}while(1);	
 return 0;		
}
