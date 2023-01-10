import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Client } from './client';
import { ClientService } from './client.service';

@Component({
  selector: 'app-clients-page',
  templateUrl: './clients-page.component.html',
  styleUrls: ['./clients-page.component.css']
})
export class ClientsPageComponent implements OnInit{

  public clients: Client[] = [];
  public updateClient: Client | undefined;
  public deleteClient: Client | undefined;

  constructor(private clientService: ClientService){}

  ngOnInit() {
    this.getClients();
  }

  public getClients(): void {
    this.clientService.getClients().subscribe({
      next: (response: Client[]) => {
        this.clients = response;
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }

  public onAddClient(addForm: NgForm): void {
    document.getElementById('add-client-form')?.click();
    this.clientService.addClient(addForm.value).subscribe(
      {
        next:(response: Client) => {
          console.log(response);
          alert('Client Created')
          this.getClients();
          addForm.reset();
        },
        error:() => { 
          alert('Client already exists or It is underage.');
          addForm.reset();
        }
      });
  }

  public onUpdateClient(client: Client): void {
    this.updateClient = client;
    this.clientService.editClient(client).subscribe({
      next:(response: Client) => {
        console.log(response);
        alert('Client Updated')
        this.getClients();
      },
      error:() => {
        alert('Client must not be underage or Number Id already exists')
      }
    });
  }

  public onDeleteClient(clientId: number): void {
    this.clientService.deleteClient(clientId).subscribe({
      next:(response: void) => {
        console.log(response);
        alert('Client Deleted')
        this.getClients();
      },
      error:() => {
        alert('This client can not be deleted ... Check the status of the accounts!')
      }
    });
  }

  public searchClient(key: string): void {
    const results: Client[] = [];
    for (const client of this.clients) {
      if (client.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || client.numberId.toLowerCase().indexOf(key.toLowerCase()) !== -1)
      {
        results.push(client);
      }
    }

    this.clients = results;

    if (results.length === 0 || !key) {
      this.getClients();
    } 

  }


  public onOpenModal(mode: string, client?: Client): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addClientModal');
    }
    else if (mode === 'edit') {
      this.updateClient = client;
      button.setAttribute('data-target', '#updateClientModal');
    }
    else if (mode === 'delete') {
      this.deleteClient = client;
      button.setAttribute('data-target', '#deleteClientModal');
    }
    container?.appendChild(button);
    button.click();
  }
}
