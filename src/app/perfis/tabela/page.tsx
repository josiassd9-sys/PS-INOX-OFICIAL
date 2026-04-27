
"use client";

import * as React from "react";
import {
  ColumnDef,
  useReactTable,
  getCoreRowModel,
  getFilteredRowModel,
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
import { Card, CardHeader, CardTitle, CardDescription, CardContent } from "@/components/ui/card";
import { perfisData } from "@/lib/data/index";
import { Dashboard } from "@/components/dashboard";
import { Input } from "@/components/ui/input";
import { Search } from "lucide-react";
import { Label } from "@/components/ui/label";


function TableComponent() {
  const [filters, setFilters] = React.useState({
    minPeso: "",
    minH: "",
    minWx: "",
  });

  type Perfil = (typeof perfisData)[0];
  const columns: ColumnDef<Perfil>[] = [
    {
      accessorKey: "nome",
      header: "Perfil",
      cell: ({ row }: { row: any }) => <div className="font-medium">{row.getValue("nome")}</div>,
      enablePinning: true,
    },
    {
      accessorKey: "peso",
      header: "Peso (kg/m)",
      cell: ({ row }: { row: any }) => row.getValue("peso"),
      enablePinning: true,
    },
    { accessorKey: "h", header: "h (mm)" },
    { accessorKey: "b", header: "b (mm)" },
    { accessorKey: "tw", header: "tw (mm)" },
    { accessorKey: "tf", header: "tf (mm)" },
    { accessorKey: "Ix", header: "Ix (cm⁴)" },
    { accessorKey: "Wx", header: "Wx (cm³)" },
    { accessorKey: "rx", header: "rx (cm)" },
    { accessorKey: "Iy", header: "Iy (cm⁴)" },
    { accessorKey: "Wy", header: "Wy (cm³)" },
    { accessorKey: "ry", header: "ry (cm)" },
  ];

  const handleFilterChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { id, value } = e.target;
    setFilters(prev => ({ ...prev, [id]: value }));
  };

  const tableData = React.useMemo(() => {
    const minPeso = parseFloat(filters.minPeso) || 0;
    const minH = parseFloat(filters.minH) || 0;
    const minWx = parseFloat(filters.minWx) || 0;

    return perfisData.filter((perfil) => {
      const pesoMatch = !minPeso || perfil.peso >= minPeso;
      const hMatch = !minH || perfil.h >= minH;
      const wxMatch = !minWx || perfil.Wx >= minWx;
      return pesoMatch && hMatch && wxMatch;
    });
  }, [filters]);

  const table = useReactTable({
    data: tableData,
    columns,
    getCoreRowModel: getCoreRowModel(),
    getFilteredRowModel: getFilteredRowModel(),
    enablePinning: true,
    initialState: {
      columnPinning: { left: ["nome", "peso"] },
    },
  });

  return (
    <div className="w-full max-w-screen-md mx-auto p-4">
      <Card>
        <CardHeader>
          <CardTitle className="text-2xl">Tabela de Perfis W</CardTitle>
          <CardDescription>
            Consulte as propriedades geométricas e físicas dos perfis de aço padrão W (Gerdau/Açominas).
          </CardDescription>

          {/* Campo de busca duplicado removido, mantendo apenas o campo dedicado de busca de perfil */}

          <div className="flex flex-wrap gap-4 mt-6">
            <div className="flex-1 min-w-[140px] w-full sm:w-auto">
              <Label htmlFor="minPeso">Peso ≥ (kg/m)</Label>
              <Input id="minPeso" type="number" placeholder="Ex: 20" value={filters.minPeso} onChange={handleFilterChange} className="w-full" />
            </div>
            <div className="flex-1 min-w-[140px] w-full sm:w-auto">
              <Label htmlFor="minH">Altura ≥ (mm)</Label>
              <Input id="minH" type="number" placeholder="Ex: 250" value={filters.minH} onChange={handleFilterChange} className="w-full" />
            </div>
            <div className="flex-1 min-w-[140px] w-full sm:w-auto">
              <Label htmlFor="minWx">Wx ≥ (cm³)</Label>
              <Input id="minWx" type="number" placeholder="Ex: 300" value={filters.minWx} onChange={handleFilterChange} className="w-full" />
            </div>
          </div>
        </CardHeader>

        <CardContent className="p-0">
          <div className="border border-border rounded-md overflow-x-auto overflow-y-auto max-h-[580px] bg-card">
            <table className="w-full table-auto min-w-max text-xs border-collapse">
              <thead className="sticky top-0 z-50 bg-muted">
                {table.getHeaderGroups().map((headerGroup: any) => (
                  <tr key={headerGroup.id}>
                    {headerGroup.headers.map((header: any) => {
                      const isPinned = header.column.getIsPinned();
                      // Customização do cabeçalho: símbolo em cima, unidade embaixo
                      let label = "";
                      let unit = "";
                      switch (header.column.id) {
                        case "peso":
                          label = "Peso";
                          unit = "kg/m";
                          break;
                        case "h":
                          label = "h";
                          unit = "mm";
                          break;
                        case "b":
                          label = "b";
                          unit = "mm";
                          break;
                        case "tw":
                          label = "tw";
                          unit = "mm";
                          break;
                        case "tf":
                          label = "tf";
                          unit = "mm";
                          break;
                        case "Ix":
                          label = "Ix";
                          unit = "cm⁴";
                          break;
                        case "Wx":
                          label = "Wx";
                          unit = "cm³";
                          break;
                        case "rx":
                          label = "rx";
                          unit = "cm";
                          break;
                        case "Iy":
                          label = "Iy";
                          unit = "cm⁴";
                          break;
                        case "Wy":
                          label = "Wy";
                          unit = "cm³";
                          break;
                        case "ry":
                          label = "ry";
                          unit = "cm";
                          break;
                        default:
                          const rendered = flexRender(header.column.columnDef.header, header.getContext());
                          label = typeof rendered === "string" ? rendered : (rendered?.toString?.() ?? "");
                      }
                      return (
                        <th
                          key={header.id}
                          colSpan={header.colSpan}
                          className={`px-1 py-1 font-semibold border-r border-border text-left align-middle ${
                            isPinned === "left"
                              ? "sticky left-0 z-50 bg-muted shadow-[4px_0_8px_-4px_rgba(0,0,0,0.15)]"
                              : ""
                          }`}
                          // Removido width/minWidth para colunas se ajustarem ao conteúdo
                        >
                          {header.column.id === "nome"
                            ? flexRender(header.column.columnDef.header, header.getContext())
                            : (
                                <div className="flex flex-col leading-tight">
                                  <span>{label}</span>
                                  {unit && <span className="text-[10px] text-muted-foreground -mt-0.5">({unit})</span>}
                                </div>
                              )}
                        </th>
                      );
                    })}
                  </tr>
                ))}
              </thead>
              <tbody>
                {table.getRowModel().rows.length > 0 ? (
                  table.getRowModel().rows.map((row: any) => (
                    <tr key={row.id} className="hover:bg-muted/50 border-b text-xs">
                      {row.getVisibleCells().map((cell: any) => {
                        const isPinned = cell.column.getIsPinned();
                        return (
                          <td
                            key={cell.id}
                            className={`px-1 py-1 border-r border-border align-middle ${
                              isPinned === "left"
                                ? "sticky left-0 z-40 bg-card shadow-[4px_0_8px_-4px_rgba(0,0,0,0.12)]"
                                : ""
                            }`}
                            // Removido width/minWidth para células se ajustarem ao conteúdo
                          >
                            {flexRender(cell.column.columnDef.cell, cell.getContext())}
                          </td>
                        );
                      })}
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan={12} className="h-32 text-center text-muted-foreground">
                      Nenhum perfil encontrado.
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>

          <p className="text-xs text-center text-muted-foreground mt-3 md:hidden">
            Arraste horizontalmente para ver todas as colunas →
          </p>
        </CardContent>
      </Card>
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

