"use client";

import * as React from "react";
import {
  ColumnDef,
  useReactTable,
  getCoreRowModel,
  flexRender,
} from "@tanstack/react-table";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Card, CardHeader, CardTitle, CardDescription } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { perfisData } from "@/lib/data/index";
import { Dashboard } from "@/components/dashboard";

function TableComponent() {
  const [filters, setFilters] = React.useState({
    minPeso: "",
    minH: "",
    minWx: "",
  });

  type Perfil = (typeof perfisData)[0];

  const columns: ColumnDef<Perfil>[] = [
    { accessorKey: "nome", header: "Perfil" },
    { accessorKey: "peso", header: "Peso (kg/m)" },
    { accessorKey: "h", header: "h (mm)" },
    { accessorKey: "b", header: "b (mm)" },
    { accessorKey: "tw", header: "tw (mm)" },
    { accessorKey: "tf", header: "tf (mm)" },
    { accessorKey: "Ix", header: "Ix (cm⁴)" },
    { accessorKey: "Wx", header: "Wx (cm³)" },
  ];

  const handleFilterChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target;
    setFilters((prev) => ({ ...prev, [id]: value }));
  };

  // Filtragem dos dados
  const filteredData = React.useMemo(() => {
    return perfisData.filter((perfil) => {
      const pesoOk = !filters.minPeso || perfil.peso >= Number(filters.minPeso);
      const hOk = !filters.minH || perfil.h >= Number(filters.minH);
      const wxOk = !filters.minWx || (perfil.Wx || 0) >= Number(filters.minWx);
      return pesoOk && hOk && wxOk;
    });
  }, [filters]);

  const table = useReactTable({
    data: filteredData,
    columns,
    getCoreRowModel: getCoreRowModel(),
  });

  return (
    <div className="flex flex-col h-screen overflow-hidden p-1">
      {/* Card Superior */}
      <Card className="flex-shrink-0 mb-3">
        <CardHeader className="pb-4">
          <CardTitle className="text-2xl">Tabela de Perfis W</CardTitle>
          <CardDescription className="text-xs">
            Consulte as propriedades geométricas e físicas dos perfis de aço padrão W (Gerdau/Açominas).
          </CardDescription>

          {/* Filtros */}
          <div className="grid grid-cols-3 gap-4 mt-5">
            <div>
              <Label htmlFor="minPeso">Peso ≥</Label>
              <Input
                id="minPeso"
                type="number"
                placeholder="Ex: 20"
                value={filters.minPeso}
                onChange={handleFilterChange}
                className="mt-1"
              />
              <p className="text-[10px] text-muted-foreground mt-0.5">(kg/m)</p>
            </div>
            <div>
              <Label htmlFor="minH">Altura ≥</Label>
              <Input
                id="minH"
                type="number"
                placeholder="Ex: 250"
                value={filters.minH}
                onChange={handleFilterChange}
                className="mt-1"
              />
              <p className="text-[10px] text-muted-foreground mt-0.5">(mm)</p>
            </div>
            <div>
              <Label htmlFor="minWx">Wx ≥</Label>
              <Input
                id="minWx"
                type="number"
                placeholder="Ex: 300"
                value={filters.minWx}
                onChange={handleFilterChange}
                className="mt-1"
              />
              <p className="text-[10px] text-muted-foreground mt-0.5">(cm³)</p>
            </div>
          </div>
        </CardHeader>
      </Card>

      {/* Sub-Container com Rolagem Horizontal e Vertical */}
      <div className="flex-1 min-h-0 border rounded-lg bg-background overflow-hidden flex flex-col">
        <div className="flex-1 min-h-0 overflow-auto">
          <div className="inline-block min-w-full"> {/* Essencial para scroll horizontal */}
            <Table>
              <TableHeader className="sticky top-0 bg-background z-10">
                {table.getHeaderGroups().map((headerGroup) => (
                  <TableRow key={headerGroup.id}>
                    {headerGroup.headers.map((header) => (
                      <TableHead 
                        key={header.id}
                        className="font-semibold whitespace-nowrap px-4 py-3"
                      >
                        {flexRender(
                          header.column.columnDef.header,
                          header.getContext()
                        )}
                      </TableHead>
                    ))}
                  </TableRow>
                ))}
              </TableHeader>

              <TableBody>
                {table.getRowModel().rows.length > 0 ? (
                  table.getRowModel().rows.map((row) => (
                    <TableRow key={row.id}>
                      {row.getVisibleCells().map((cell) => (
                        <TableCell 
                          key={cell.id}
                          className="px-4 py-3 whitespace-nowrap"
                        >
                          {flexRender(cell.column.columnDef.cell, cell.getContext())}
                        </TableCell>
                      ))}
                    </TableRow>
                  ))
                ) : (
                  <TableRow>
                    <TableCell 
                      colSpan={columns.length} 
                      className="h-32 text-center text-muted-foreground"
                    >
                      Nenhum perfil encontrado com os filtros aplicados.
                    </TableCell>
                  </TableRow>
                )}
              </TableBody>
            </Table>
          </div>
        </div>
      </div>
    </div>
  );
}

export default function Page() {
  return (
    <Dashboard initialCategoryId="perfis/tabela-w">
      <TableComponent />
    </Dashboard>
  );
}